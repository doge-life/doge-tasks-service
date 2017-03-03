require 'spec_helper'

describe 'doge-tasks-service dependencies' do
  describe command('java -version') do
    its(:stderr) { should contain 'openjdk version "1.8' }
  end
end
